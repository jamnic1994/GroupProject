# To run:

1. Stop + delete any running containers and images.
2. Delete target directory
3. Set CI in .env file to "false" (Otherwise it will run through all menu options automatically)
4. Build project (ctrl + F9)
5. Compile in Maven
6. Package in Maven
7. Run docker-compose.yml
8. Open terminal
9. Input: docker exec -it <container_id_or_name> /bin/sh
10. Press enter
11. Input: java -jar /tmp/seMethods-0.1-alpha-4-jar-with-dependencies.jar
12. Press enter
13. You can now use the menu on the terminal!!

# If pushing to github repo!!

Set CI in .env file to "true" (Otherwise github workspace won't complete)

![GitHub Workflow Status (branch)](https://img.shields.io/github/actions/workflow/status/jamnic1994/groupproject/main.yml?branch=master)

[![LICENSE](https://img.shields.io/github/license/jamnic1994/sem.svg?style=flat-square)](https://github.com/jamnic1994/groupproject/blob/master/LICENSE)

# Software Engineering Methods
- **Master Build Status:** [![Master Build Status](https://img.shields.io/github/actions/workflow/status/jamnic1994/groupproject/main.yml?branch=master)](https://github.com/jamnic1994/groupproject/tree/master)
- **Develop Branch Status:** [![Develop Branch Status](https://img.shields.io/github/actions/workflow/status/jamnic1994/groupproject/main.yml?branch=develop)](https://github.com/jamnic1994/groupproject/tree/develop)
- **License:** [![LICENSE](https://img.shields.io/github/license/jamnic1994/groupproject.svg?style=flat-square)](https://github.com/jamnic1994/groupproject/blob/master/LICENSE)
- **Release:** [![Releases](https://img.shields.io/github/release/jamnic1994/groupproject/all.svg?style=flat-square)](https://github.com/jamnic1994/groupproject/releases)


