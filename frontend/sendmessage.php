<?php
$sender = "User"; // Or get from session/login if you have one
$message = $_POST['message'];

$conn = new mysqli('localhost', 'root', '', 'vehiclepro_db');
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$stmt = $conn->prepare("INSERT INTO chatsupport (sender, message) VALUES (?, ?)");
$stmt->bind_param("ss", $sender, $message);
$stmt->execute();
$stmt->close();
$conn->close();

header("Location: chatsupport.php");
exit();
?>