# fileService
The application can be used for upload of a csv file, retrieval and deletion of updated data.
Sample files used for testing were provided in the /test/resources folder
Super CSV third-party library which is under Apache License, Version 2.0 is used for parsing the CSV file.
H2 in-memory database is used for insertion, retrieval and deletion of data
The file is parsed and the objects extracted are split to chunks of 500(a configurable parameter) and stored in db.

The Headers are configurable. The file is validated against the headers provided and constrainints are created based on the requirement.
The uniqueness of primary key is maintained with the last record data is retained and the others are ignored.
The API has an inbuilt feature of ignoring empty lines and so it is not explicilty taken care of.

In case of any error in processing file, the file processing stops, the error line will be reported to the user.
No data will be stored in database in this scenario
This can be enhanced to partially process the file and report all the errors at once.


A datapoint table is created with the details of the file and a unique id
The unique datapointid along with the data is stored is saved in the db.

