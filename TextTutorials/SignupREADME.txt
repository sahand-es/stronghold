1.
In order to create a user you must give this command and fill the fields that have <>:
    user create -u <username> -p <password> <confirmPassword> -n <nickname> -email <email.email@.com> (-s <slogan>)?

If you want the program to give you random password you must give this command:
    user create -u <username> -p random -n <nickname> -email <email.email@.com>

If you want the password and the slogan to be generated randomly this command is needed:
    user create -u <username> -p random -n <nickname> -email <email.email@.com> -s random

*Keep in mind that there shouldn't be any extra white-spaces and the part which is in parentheses isn't mandatory to be given.
Also it's possible to give the arguments in different orders.

2.
If there wasn't any errors in the previous part, then you would be asked for an answer to a security question.
*this part's tutorial is given in command line.

3.
Eventually you must verify the captcha for your username to be submitted.

4.
If everything is correct you would receive a message and then referred to LoginMenu.


*There are two other commands in this Menu:
    1) "exit" : program will be terminated.
    2) "back" : you would go back to Login Menu.