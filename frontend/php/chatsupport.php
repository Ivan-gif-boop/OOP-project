<?php
// chatsupport.php
?>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Chat Support</title>
  <link rel="stylesheet" href="chatsupport.css">
</head>
<body>
  <div class="chat-container">
    <h2>Chat Support</h2>
    <div class="chat-box">
      <div class="chat-header">
        <h3>Live Chat Support</h3>
        <p>Our support team is here to help with any questions</p>
      </div>
      <div class="chat-messages" id="messages">
        <?php
        // Load messages from DB
        $conn = new mysqli('localhost', 'root', '', 'vehiclepro_db');
        if ($conn->connect_error) { die("Connection failed: " . $conn->connect_error); }

        $result = $conn->query("SELECT * FROM chatsupport ORDER BY timestamp ASC");
        while($row = $result->fetch_assoc()) {
          echo "<div class='message'><strong>{$row['sender']}:</strong> {$row['message']} <span class='time'>{$row['timestamp']}</span></div>";
        }
        $conn->close();
        ?>
      </div>
      <form class="chat-input" action="sendmessage.php" method="POST">
        <input type="text" name="message" placeholder="Type your message..." required>
        <button type="submit">&#9658;</button>
      </form>
    </div>
  </div>
</body>
</html>
