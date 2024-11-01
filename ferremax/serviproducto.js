const express = require('express');
const cors = require('cors');
const mysql = require('mysql2');

// Configuración de la base de datos
const db = mysql.createConnection({
    host: 'localhost', // Cambia esto si tu DB no está en localhost
    user: 'root', // Cambia esto si tienes otro usuario
    password: '', // Añade tu contraseña aquí si la tienes
    database: 'ferremax_db' // Asegúrate de que el nombre de la base de datos es correcto
});

// Conexión a la base de datos
db.connect(err => {
    if (err) {
        console.error('Error al conectar a la base de datos: ', err);
        return;
    }
    console.log('Conexión exitosa a la base de datos ferremax_db');
});

const app = express();
const PORT = 5021; // Puerto donde se ejecutará el servidor

// Middleware
app.use(cors());
app.use(express.json());

// Ruta para obtener todos los productos
app.get('/api/productos', (req, res) => {
    const sql = 'SELECT * FROM producto';
    db.query(sql, (err, results) => {
        if (err) {
            return res.status(500).json({ error: err.message });
        }
        res.json(results);
    });
});

// Ruta para crear un nuevo producto
app.post('/api/productos', (req, res) => {
    const { Nombre, Descripcion, precio_unitario, Marca, Codigo_Barras, ID_Categoria, cantidad, precio } = req.body;

    // Verifica que todos los campos requeridos están presentes
    if (!Nombre || !precio_unitario || !Marca || !Codigo_Barras || !cantidad) {
        return res.status(400).json({ error: 'Faltan datos requeridos' });
    }

    // Aquí iría la lógica para insertar en la base de datos
    const query = `
        INSERT INTO producto (Nombre, Descripcion, precio_unitario, Marca, Codigo_Barras, ID_Categoria, cantidad, precio)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)`;

    const values = [Nombre, Descripcion, precio_unitario, Marca, Codigo_Barras, ID_Categoria, cantidad, precio];

    db.query(query, values, (error, results) => {
        if (error) {
            return res.status(500).json({ error: error.message });
        }
        res.status(201).json({ message: 'Producto creado', id: results.insertId });
    });
});

// Ruta para actualizar un producto
app.put('/api/productos/editar/:id', (req, res) => {
    const { id } = req.params;
    const { Nombre, Descripcion, precio_unitario, Marca, Codigo_Barras, ID_Categoria, cantidad, precio } = req.body;

    const sql = 'UPDATE producto SET Nombre = ?, Descripcion = ?, precio_unitario = ?, Marca = ?, Codigo_Barras = ?, ID_Categoria = ?, cantidad = ?, precio = ? WHERE ID_Producto = ?';
    const values = [Nombre, Descripcion, precio_unitario, Marca, Codigo_Barras, ID_Categoria, cantidad, precio, id];

    db.query(sql, values, (err, results) => {
        if (err) {
            return res.status(500).json({ error: err.message });
        }
        if (results.affectedRows === 0) {
            return res.status(404).json({ message: 'Producto no encontrado' });
        }
        res.json({ message: 'Producto actualizado' });
    });
});

// Ruta para eliminar un producto
app.delete('/api/productos/eliminar/:id', (req, res) => {
    const { id } = req.params;
    const sql = 'DELETE FROM producto WHERE ID_Producto = ?';
    
    db.query(sql, id, (err, results) => {
        if (err) {
            return res.status(500).json({ error: err.message });
        }
        if (results.affectedRows === 0) {
            return res.status(404).json({ message: 'Producto no encontrado' });
        }
        res.json({ message: 'Producto eliminado exitosamente' });
    });
});

// Ruta para obtener un producto específico
app.get('/api/productos/:id', (req, res) => {
    const { id } = req.params;
    const sql = 'SELECT * FROM producto WHERE ID_Producto = ?';
    
    db.query(sql, id, (err, results) => {
        if (err) {
            return res.status(500).json({ error: err.message });
        }
        if (results.length === 0) {
            return res.status(404).json({ message: 'Producto no encontrado' });
        }
        res.json(results[0]);
    });
});

// Iniciar el servidor
app.listen(PORT, () => {
    console.log(`API escuchando en http://localhost:${PORT}`);
});
