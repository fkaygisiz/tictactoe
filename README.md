# tictactoe

Please download the code by using 
git clone https://github.com/fkaygisiz/tictactoe.git

and then get into tictactoe folder.

if your OS is Windows run
 .\mvnw.cmd clean install
 
 else run
  .\mvnw clean install
  
  this command will compile the code and run the tests.
  
  get into target folder by calling 
  cd target
  
  
 config file:
 -----------------------
 Your config file should be like (player symbols should be 1 character, - is reserved, board width can be min 3 max 10) :<br />
  
  &lt;first player symbol &gt;<br />
  &lt;second player symbol&gt;<br />
  &lt;computer symbol&gt;<br />
  &lt;board width (min 3 max 10) &gt;
  
  sample file content:<br />
  a<br />
  b<br />
  c<br />
  4
  
  -----------------------
  run 
  java -jar .\TicTacToeForThree-0.0.1-SNAPSHOT.jar <configuration_file_path>
  
  eg:
  java -jar .\TicTacToeForThree-0.0.1-SNAPSHOT.jar C:\temp\tictactoe.txt
