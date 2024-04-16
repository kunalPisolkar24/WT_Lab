<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <title>Document</title>
</head>
<body>
  <div class="container mt-5">
  <?php
  $servername = "localhost";
  $username = "root";
  $password = "";
  $dbname = "test1";

  // Create connection
  $conn = new mysqli($servername, $username, $password);

  // Check connection
  if ($conn->connect_error) {
      die("Connection failed: " . $conn->connect_error);
  }

  function createDatabase($conn, $dbname)
  {
      $sql = "CREATE DATABASE IF NOT EXISTS $dbname";

      if ($conn->query($sql) === true) {
          echo "<div class='alert alert-success mb-3' role='alert'>Database created successfully</div>";
      } else {
          echo "<div class='alert alert-danger mb-3' role='alert'>Error creating database: " .
              $conn->error .
              "</div>";
      }
  }

  function createTable($conn)
  {
      $sql = "CREATE TABLE IF NOT EXISTS users (
    id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(200) NOT NULL,
    age INT(3) NOT NULL
  )";

      if ($conn->query($sql) === true) {
          echo "<div class='alert alert-success mb-3' role='alert'>Table users created successfully</div>";
      } else {
          echo "<div class='alert alert-danger mb-3' role='alert'>Error creating table: " .
              $conn->error .
              "</div>";
      }
  }

  function insert($conn, $name, $age)
  {
      $sql = "INSERT INTO users (full_name, age) VALUES ('$name', $age)";

      if ($conn->query($sql) === true) {
          echo "<div class='alert alert-success mb-3' role='alert'>New record created successfully</div>";
      } else {
          echo "<div class='alert alert-danger mb-3' role='alert'>Error: " .
              $sql .
              "<br>" .
              $conn->error .
              "</div>";
      }
  }

  function update($conn, $id, $name, $age)
  {
      $sql = "UPDATE users SET full_name='$name', age=$age WHERE id=$id";

      if ($conn->query($sql) === true) {
          echo "<div class='alert alert-success mb-3' role='alert'>Record updated successfully</div>";
      } else {
          echo "<div class='alert alert-danger mb-3' role='alert'>Error updating record: " .
              $conn->error .
              "</div>";
      }
  }

  function delete($conn, $id)
  {
      $sql = "DELETE FROM users WHERE id=$id";

      if ($conn->query($sql) === true) {
          echo "<div class='alert alert-success mb-3' role='alert'>Record deleted successfully</div>";
      } else {
          echo "<div class='alert alert-danger mb-3' role='alert'>Error deleting record: " .
              $conn->error .
              "</div>";
      }
  }

  function display($conn)
  {
      $sql = "SELECT id, full_name, age FROM users";
      $result = $conn->query($sql);

      if ($result->num_rows > 0) {
          echo "<table class='table'>";
          echo "<thead><tr><th>ID</th><th>Name</th><th>Age</th></tr></thead>";
          echo "<tbody>";
          // output data of each row
          while ($row = $result->fetch_assoc()) {
              echo "<tr><td>" .
                  $row["id"] .
                  "</td><td>" .
                  $row["full_name"] .
                  "</td><td>" .
                  $row["age"] .
                  "</td></tr>";
          }
          echo "</tbody>";
          echo "</table>";
      } else {
          echo "0 results\n";
      }
  }

  function main($conn)
  {
      createDatabase($conn, "test1");
      mysqli_select_db($conn, "test1");
      createTable($conn);
      insert($conn, "John Wick", 45);
      insert($conn, "Walter White", 25);
      insert($conn, "Jesse Pinkman", 35);
      insert($conn, "Skyler White", 25);
      update($conn, 2, "Walter White", 46);
      update($conn, 4, "Skyler White", 44);
      delete($conn, 1);
      display($conn);
  }

  main($conn);

  $conn->close();
  ?>
  </div>

</body>
</html>
