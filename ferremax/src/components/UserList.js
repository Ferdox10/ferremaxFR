import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

const UserList = () => {
    const [usuarios, setUsuarios] = useState([]);
    const [mensaje, setMensaje] = useState('');

    useEffect(() => {
        const fetchUsuarios = async () => {
            const response = await axios.get('http://localhost:5000/api/usuarios');
            setUsuarios(response.data);
        };
        fetchUsuarios();
    }, []);

    const handleDelete = async (id) => {
        await axios.delete(`http://localhost:5000/api/usuarios/eliminar/${id}`);
        const updatedUsuarios = usuarios.filter(usuario => usuario.id !== id);
        setUsuarios(updatedUsuarios);
        setMensaje('Usuario eliminado exitosamente');
    };

    return (
        <div className="container mt-4 bg-light p-4 rounded shadow">
            <h1 className="text-center">Lista de Usuarios</h1>
            {mensaje && <div className="alert alert-success">{mensaje}</div>}
            <Link to="/usuarios/nuevo" className="btn btn-primary mb-3">Agregar Usuario</Link>
            <table className="table table-striped table-hover">
                <thead className="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Email</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {usuarios.map(usuario => (
                        <tr key={usuario.id}>
                            <td>{usuario.id}</td>
                            <td>{usuario.nombre}</td>
                            <td>{usuario.email}</td>
                            <td>
                                <Link to={`/usuarios/editar/${usuario.id}`} className="btn btn-warning">Editar</Link>
                                <button className="btn btn-danger" onClick={() => handleDelete(usuario.id)}>Eliminar</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default UserList;