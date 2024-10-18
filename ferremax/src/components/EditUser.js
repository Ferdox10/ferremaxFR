import React, { useEffect, useState } from 'react';
import axios from 'axios';
import UserForm from './UserForm';
import { useNavigate, useParams } from 'react-router-dom';

const EditUser = () => {
    const [usuario, setUsuario] = useState(null);
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        const fetchUsuario = async () => {
            try {
                const response = await axios.get(`http://localhost:5000/api/usuarios/${id}`);
                if (response.data) {
                    setUsuario(response.data);
                } else {
                    console.error('Usuario no encontrado');
                }
            } catch (error) {
                console.error('Error al obtener el usuario:', error);
            }
        };
        fetchUsuario();
    }, [id]);

    const handleUpdate = async (data) => {
        await axios.put(`http://localhost:5000/api/usuarios/editar/${data.id}`, data);
        navigate('/usuarios');
    };

    if (!usuario) return <div>Cargando...</div>;

    return (
        <div className="container mt-4">
            <h1>Editar Usuario</h1>
            <UserForm usuario={usuario} onSubmit={handleUpdate} />
        </div>
    );
};

export default EditUser;
