import React, { useState } from 'react';
import axios from 'axios';

const UserForm = ({ usuario, onSubmit }) => {
    const [nombre, setNombre] = useState(usuario ? usuario.nombre : '');
    const [email, setEmail] = useState(usuario ? usuario.email : '');
    const [password, setPassword] = useState('');
    const [mensaje, setMensaje] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        const data = { 
            id: usuario ? usuario.id : Date.now().toString(), 
            nombre, 
            email, 
            password 
        };

        // Verifica si el usuario ya existe (solo al crear)
        if (!usuario) {
            const response = await axios.get('http://localhost:5000/api/usuarios');
            const existe = response.data.some(u => u.email === email);
            if (existe) {
                setMensaje('El usuario ya existe');
                return;
            }
        }

        await onSubmit(data);
        setMensaje(usuario ? 'Usuario actualizado exitosamente' : 'Usuario creado exitosamente');
        setNombre('');
        setEmail('');
        setPassword('');
    };

    return (
        <form onSubmit={handleSubmit}>
            {mensaje && <div className="alert alert-info">{mensaje}</div>}
            <div className="form-group">
                <label>Nombre:</label>
                <input type="text" className="form-control" value={nombre} onChange={(e) => setNombre(e.target.value)} required />
            </div>
            <div className="form-group">
                <label>Email:</label>
                <input type="email" className="form-control" value={email} onChange={(e) => setEmail(e.target.value)} required />
            </div>
            <div className="form-group">
                <label>Password:</label>
                <input type="password" className="form-control" value={password} onChange={(e) => setPassword(e.target.value)} required />
            </div>
            <button type="submit" className="btn btn-success">Guardar</button>
        </form>
    );
};

export default UserForm;