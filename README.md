play-grunt
==========

Sample of a Grunt.js build integrated into a Play project (in sbt)

The JavaScript application resides in the folder "ui".
The JavaScript application is completely separated from the Play! application.
It can be possible to build the JavaScript application separately.


Requirements:
- npm must be installed.


1. Start the play console (with 'play' or 'sbt')


2. To install all JavaScript dependencies:
npm install

Later: To update all JavaScript dependencies:
npm update


3. Run the application
run

Grunt runs automatically one time.
Then Grunt is watching for modifications and compiles again when necessary.


4. Open the index page http://localhost:9000/
The JavaScript file "ui/src/play-grunt.js" should display "Grunt is integrated" on the page.
