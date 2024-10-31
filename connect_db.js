const mysql = require('mysql');

// Configuración de conexión
const connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '',
  database: 'ferremax_db'
});

// Conectar a la base de datos y listar tablas
connection.connect((error) => {
  if (error) {
    return console.error('Error al conectar a la base de datos:', error.message);
  }
  console.log('Conexión exitosa a la base de datos ferremax_db');

  // Consulta para listar todas las tablas en la base de datos
  const query = 'SHOW TABLES';

  connection.query(query, (error, results) => {
    if (error) {
      return console.error('Error al obtener las tablas:', error.message);
    }

    console.log('Tablas en la base de datos ferremax_db:');
    results.forEach((row) => {
      console.log(row[`Tables_in_ferremax_db`]); // Imprime el nombre de cada tabla
    });

    // Cerrar la conexión después de listar las tablas
    connection.end((err) => {
      if (err) {
        return console.error('Error al cerrar la conexión:', err.message);
      }
      console.log('Conexión cerrada');
    });
  });
});
