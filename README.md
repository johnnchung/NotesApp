# Notes Application

## Goal
Develop a standalone application designed for recording, searching, and deleting short, adhoc notes. 

## Team Members
Yang Ji, John Chung, Hammad Khan, Tim Chung

## Quick-start
# Windows:
1. Download the connectnotesinstance.pem file from GitLab EC2Key folder. <br />
2. SSH into AWS EC2 server instance with the following commands: <br />
```ssh -i <Path-To-connectnotesinstance.pem> ec2-user@ec2-54-80-234-31.compute-1.amazonaws.com ```<br />
3. Start the Spring Server by running ```java -jar demo-0.0.1-SNAPSHOT.jar```
4. Download the application from the installer in GitLab releases folder, and run the application!. <br />
5. The application can also be ran through the console, by calling Notes.sh

# MacOS (Similar to above with additional steps):
1. Download the connectnotesinstance.pem file from GitLab EC2Key folder. <br />
2. Change owner file permissions of the .pem file by running ```sudo chown <User-Name> <Path-To-connectnotesinstance.pem>``` <br />
3. Allow user, group, and other permissions by running ```chmod 400 <Path-To-connectnotesinstance.pem>``` <br />
4. Follow steps 2-5 above!

## Supported gradle tasks:

| Tasks   | Description                                          |
|:--------|:-----------------------------------------------------|
| clean   | Remove build/ directory                              |
| build   | Build the application project in build/ directory    |
| run     | Run the application or console project               |
| distZip | Create run scripts in application/build/distribution |
| distTar | Create run scripts in application/build/distribution |


## Releases
Version 1.0: released Feburary 17th, 2023<br />
	- release-notes (in releases folder)<br />
	- Installers (Windows, MacOS)<br />
Version 2.0: released March 10th, 2023 <br />
	- release-notes (in releases folder)<br />
	- Installers (Windows, MacOS)<br />
Version 3.0: released March 24th, 2023 <br />
	- release-notes (in releases folder)<br />
	- Installers (Windows, MacOS)<br />
Version 3.0: released April 10th, 2023 <br />
	- release-notes (in releases folder)<br />
	- Installers (Windows, MacOS)<br />
