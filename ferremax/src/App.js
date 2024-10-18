import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import axios from 'axios';
import UserList from './components/UserList';
import UserForm from './components/UserForm';
import EditUser from './components/EditUser';

const App = () => {
    const handleCreate = async (data) => {
        await axios.post('http://localhost:5000/api/usuarios', data);
        // Lógica para redirigir o mostrar un mensaje después de crear el usuario
    };

    return (
        <Router>
            <Routes>
                <Route path="/usuarios" element={<UserList />} />
                <Route path="/usuarios/nuevo" element={<UserForm onSubmit={handleCreate} />} />
                <Route path="/usuarios/editar/:id" element={<EditUser />} />
            </Routes>
        </Router>
    );
};

export default App;
