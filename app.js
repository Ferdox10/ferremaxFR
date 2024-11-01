// Importar las dependencias necesarias
const express = require('express');
const mysql = require('mysql');
const bodyParser = require('body-parser');
const app = express();
const cors = require('cors');
const port = 3001;
app.use(cors());

// Configurar el middleware
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// Conectar a la base de datos MySQL
const db = mysql.createConnection({
    host: 'localhost',
    user: 'root', // Usa tu usuario de MySQL
    password: '', // Usa la contraseña si tienes
    database: 'ferremax_db' // Reemplaza con el nombre de tu base de datos
});

db.connect((err) => {
    if (err) {
        console.error('Error al conectar a la base de datos:', err);
        return;
    }
    console.log('Conectado a la base de datos MySQL');
});

// Ruta para el registro de usuarios
app.post('/register', (req, res) => {
    const { username, email, password } = req.body;

    // Crear y ejecutar la consulta de inserción
    const query = 'INSERT INTO usuarios (username, email, password) VALUES (?, ?, ?)';
    db.query(query, [username, email, password], (err, result) => {
        if (err) {
            console.error('Error al registrar al usuario:', err);
            return res.status(500).json({ error: 'Error al registrar al usuario' });
        }
        res.json({ success: true, message: 'Usuario registrado con éxito' });
    });
});

// Ruta para el inicio de sesión
app.post('/login', (req, res) => {
    const { email, password } = req.body;

    // Crear y ejecutar la consulta de verificación
    const query = 'SELECT * FROM usuarios WHERE email = ? AND password = ?';
    db.query(query, [email, password], (err, results) => {
        if (err) {
            console.error('Error al iniciar sesión:', err);
            return res.status(500).json({ error: 'Error al iniciar sesión' });
        }

        if (results.length > 0) {
            res.json({ success: true, message: 'Inicio de sesión exitoso' });
        } else {
            res.json({ success: false, message: 'Correo o contraseña incorrectos' });
        }
    });
});

// Ruta para obtener la lista de usuarios
app.get('/usuarios', (req, res) => {
    const query = 'SELECT * FROM usuarios';

    db.query(query, (err, results) => {
        if (err) {
            console.error('Error al obtener la lista de usuarios:', err);
            return res.status(500).json({ error: 'Error al obtener la lista de usuarios' });
        }
        res.json({ success: true, usuarios: results });
    });
});

// Iniciar el servidor
app.listen(port, () => {
    console.log(`Servidor escuchando en http://localhost:${port}`);
});
