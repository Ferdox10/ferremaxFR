const express = require('express');
const cors = require('cors');
const app = express();
const PORT = 5000; // Puerto donde se ejecutará el servidor

// Middleware
app.use(cors());
app.use(express.json());

// Almacenamiento temporal de usuarios
let usuarios = [];

// Ruta para obtener todos los usuarios
app.get('/api/usuarios', (req, res) => {
    res.json(usuarios);
});

// Ruta para crear un nuevo usuario
app.post('/api/usuarios', (req, res) => {
    const userData = req.body;
    // Verificamos que los campos necesarios existan
    if (!userData.id || !userData.nombre || !userData.email) {
        return res.status(400).json({ message: 'Faltan datos requeridos' });
    }
    // Lógica para agregar el usuario
    usuarios.push(userData);
    console.log('Usuario recibido:', userData);
    res.status(201).json({ message: 'Usuario creado', user: userData });
});

// Ruta para editar un usuario
app.put('/api/usuarios/editar/:id', (req, res) => {
    const { id } = req.params;
    const updatedUser = req.body;
    // Asegurarse de que el usuario existe antes de actualizar
    const index = usuarios.findIndex(user => user.id === id);
    if (index !== -1) {
        usuarios[index] = { ...usuarios[index], ...updatedUser };
        res.json({ message: 'Usuario actualizado', user: usuarios[index] });
    } else {
        res.status(404).json({ message: 'Usuario no encontrado' });
    }
});

// Ruta para eliminar un usuario
app.delete('/api/usuarios/eliminar/:id', (req, res) => {
    const { id } = req.params;
    usuarios = usuarios.filter(user => user.id !== id);
    res.status(204).send();
});

// Ruta para obtener un usuario específico
app.get('/api/usuarios/:id', (req, res) => {
    const { id } = req.params;
    const usuario = usuarios.find(user => user.id === id);
    if (usuario) {
        res.json(usuario);
    } else {
        res.status(404).json({ message: 'Usuario no encontrado' });
    }
});

// Iniciar el servidor
app.listen(PORT, () => {
    console.log(`Api escuchando en http://localhost:${PORT}`);
});
