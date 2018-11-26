<?php
$file = "127.0.0.1/test.html";
$test = file_get_contents('1.jpg', 'a');
if (isset($_POST['test'])) {
file_put_contents($file, $_POST["test"]);
};
?>
<form action="" method="post">
<textarea id="test" name="test" style="width:100%; height:50%;"><? echo "$test"; ?></textarea>
<input type="submit" value="submit">
</form>
