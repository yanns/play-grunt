play-grunt
==========

Sample of a Grunt.js build integrated into a Play project (in sbt)<br/>
The JavaScript application resides in the folder "ui".<br/>
The JavaScript application is completely separated from the Play! application.<br/>
It is possible to build the JavaScript application separately.


Requirements
============
- npm must be installed.


First Steps
===========

- Start the play console (with 'play' or 'sbt')


- To install all JavaScript dependencies:
```
npm install
```

- Run the application
```
run
```
Grunt runs automatically one time.<br/>
Then Grunt is watching for modifications and compiles again when necessary.


- Open the index page http://localhost:9000/<br/>
The JavaScript file "ui/src/play-grunt.js" should display "Grunt is integrated" on the page.


Later
=====
- To update all JavaScript dependencies:
```
npm update
```

- To prepare the application for production:
```
stage
```
Grunt runs automatically before the application is staged.

Functions
=========
- integrate grunt build with play ```run``` command
- integrate grunt build with play ```stage``` command
