In order to create a user you must give this command and fill the fields that have <>:
user create -u <username> -p <password> <confirmPassword> -n <nickname> -email <email.email@.com> (-s <slogan>)?

If you want the program to give you random password you must give this command:
user create -u <username> -p random -n <nickname> -email <email.email@.com>

If you want the password and the slogan to be generated randomly this command is needed:
user create -u <username> -p random -n <nickname> -email <email.email@.com> -s random


*Keep in mind that there shouldn't be any extra white-spaces and the part which is in parentheses isn't mandatory to be given