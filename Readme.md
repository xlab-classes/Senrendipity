# Serendipity
An instant web chat room that matches users based on their interests

### Web chat room based on AJAX
This project is an online chat room based on AJAX polling technology, which has realized basic functions such as user registration, login, and private chat based on interest matching.
![](https://github.com/Chuanlong21/442-group/blob/main/signIn.png?raw=true)![](https://github.com/Chuanlong21/442-group/blob/main/SignUp.jpg?raw=true)![](https://github.com/Chuanlong21/442-group/blob/main/match.jpg?raw=true)![](https://github.com/Chuanlong21/442-group/blob/main/chat.png?raw=true)


# Getting Started
## Prerequisites
- Download the tomcat apache: (we are using "Tomcat 9.0.63 Released")
  ```
  https://tomcat.apache.org/ 
  ```
  
- Download IntelliJ IDEA
  ```
  https://www.jetbrains.com/idea/
  ```

  -Download and Implement SDK 11, more information: 
  ```
  https://www.jetbrains.com/help/idea/sdk.html#change-module-sdk
  ```

## Installation

### git clone our project: 
```bash
git clone https://github.com/xlab-classes/cse442-spring2022-team-heart.git
```
### Set Up And Run Tomcat Server Locally
After cloning the project, open the project in **IntelliJ IDEA** .<br />
1. Click on the upper left corner: `File -> Project Structure` <br />
![](https://github.com/Chuanlong21/442-group/blob/main/00.png?raw=true)<br /><br />

2. In `Project Structure -> Project` : make sure we are using `SDK 11`  <br />
![](https://github.com/Chuanlong21/442-group/blob/main/0.png?raw=true) <br /><br />

3. In `Project Structure -> Artifacts `: <br /> 
   Click the add button then select `Web Application:Exploded -> From Modules`<br /> 
   Select `web` and click OK:<br />
![](https://github.com/Chuanlong21/442-group/blob/main/0a.png?raw=true) <br /><br />
![](https://github.com/Chuanlong21/442-group/blob/main/0b.png?raw=true) <br /><br />
![](https://github.com/Chuanlong21/442-group/blob/main/0c.png?raw=true) <br /><br />

4. On the upper Right corner, click `Edit Configurations` to Configure the Tomcat server.<br />
![](https://github.com/Chuanlong21/442-group/blob/main/1.png?raw=true) <br /><br />

5. Click the add button, then find `Tomcat Server -> Local ` :<br />
![](https://github.com/Chuanlong21/442-group/blob/main/2.png?raw=true) <br /><br />

6. Make sure HTTP port is 8080 and URL has /SignIn.html at the end:<br />
```
URL : http://localhost:8080/web_war_exploded/SignIn.html
HTTP Port : 8080
```
![](https://github.com/Chuanlong21/442-group/blob/main/2a.png?raw=true) <br /><br />

7. Click `Configure`, then click the add button to select the root directory of the tomcat apache file we downloaded.
![](https://github.com/Chuanlong21/442-group/blob/main/3.png?raw=true) <br /><br />
Select file then press Ok: <br />
![](https://github.com/Chuanlong21/442-group/blob/main/4.png?raw=true) <br /><br />
Press Ok: <br />
![](https://github.com/Chuanlong21/442-group/blob/main/5.png?raw=true) <br /><br />

8. Click the `fix` to automatically add the artifacts:<br />
![](https://github.com/Chuanlong21/442-group/blob/main/10.png?raw=true) <br /><br />
check that `Deployment` has a file, and press ok:<br />
![](https://github.com/Chuanlong21/442-group/blob/main/11.png?raw=true) <br /><br />

9. Now we can click the run button to run the tomcat servers! (Locally) <br />
![](https://github.com/Chuanlong21/442-group/blob/main/12.png?raw=true) <br /><br />


## Features

- Users can sign-in/sign-up an account 
- Users can select or create or delete their interest label
- Users can match random people with similar interests 
- Users can add friends by searching their friend’s username
- Users can chat with other users
- Users can delete/unfriend other users
- Users can change their password if they forgot the password

## Tech
- JavaWeb
- AJAX
- Json data
- MySQL database
- Tomacat

## Contributor
[Chuanlong21](https://github.com/Chuanlong21), [HaifengXiao-29](https://github.com/HaifengXiao-29), [Haoxiang](https://github.com/Haoxiang-56), [Jingjing Weng](https://github.com/jweng6)
