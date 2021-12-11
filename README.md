# Drawing Program
This program creates, adds vertical and horizontal lines, rectangles and buckfill a canvas.

It has a simple console UI and the navigation depends on user command inputs to System.in.

Main menu in a nutshell:

    1-Create a new canvas.
        Creates a new canvas
    2- Start drawing on the canvas by issuing various commands
        Allows the user to perform the following operations:
           Command              Description
           C w h                Should create a new canvas of width w and height h.
           L x1 y1 x2 y2        Should create a new line from (x1,y1) to (x2,y2). Currently only horizontal or vertical lines are supported. Horizontal and vertical lines will be drawn using the 'x' character.
           R x1 y1 x2 y2        Should create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2). Horizontal and vertical lines will be drawn using the 'x' character.
           B x y c              Should fill the entire area connected to (x,y) with "colour" c. The behaviour of this is the same as that of the "bucket fill" tool in paint programs.
           Q                    Should quit the program.
       
    3- Quit
        Quits the program
   
Build

In order to package the program with all its dependencies, build the app using the following maven command:
mvn package

Run

Once the artifact <i>drawing-local-jar-with-dependencies.jar</i> is generated, open a bash window and run using the following command:


java -jar drawing-local-jar-with-dependencies.jar