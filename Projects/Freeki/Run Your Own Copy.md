<!-- Freeki metadata. Do not remove this section!
TITLE: Run Your Own Copy
-->
#Run Your Own Copy

A fundamental part of the shared-wiki workflow of Freeki is setting up and running local copies of the server for editing content. Establishing a local Freeki instance involves the following rough steps:

1. Fork the wiki content Git repository
2. Clone the forked content repository to your local disk
3. Download the Freeki server binary
4. Start Freeki, pointed at your Git repository clone

Additionally, after you make your local edits, you need to follow another set of rough steps to have the edits synchronized with the central website:

1. Push the changes from your Git repository clone back to your fork of the wiki content Git repository
2. Create a pull request against the original wiki content Git repository
3. (*Once the pull request is merged, the repository update hook will prompt the webserver to sync itself.*)

## Establishing a Local Freeki Instance

### Fork the Wiki Content Repository

If you don't already have one, sign up for a free user on [GitHub](http://github.com/).

Browse to the GitHub repository for the master wiki content. For example, [here is the one for the Free State Maker Society](https://github.com/jdcasey/freemaker-wiki). **Click the Fork button to get your own linked copy.**

### Clone the Forked Content Repository

Fire up a git client, and clone your new repository, using the URL listed 2/3 of the way down the right side of the GitHub page for that repository, under the heading **HTTPS URL for cloning**.

Git clients:

- [Tortoise Git](http://code.google.com/p/tortoisegit/) (Windows)
- [GitHub for Mac](http://mac.github.com/) (OS X)
- [git command line tool](http://www.git-scm.com/) (Linux, OS X, Windows?)

Make a note of the directory where you clone the content repository. If possible, it's simplest to clone it into the `freeki` directory within your home directory.

### Download Freeki

Download: [Freeki Vert.x Server](http://central.maven.org/maven2/org/commonjava/freeki/freeki-vertx-server/0.4.1/freeki-vertx-server-0.4.1.jar) (or the latest version from [here](http://central.maven.org/maven2/org/commonjava/freeki/freeki-vertx-server/)).

### Start Freeki

Start a command shell and type:

    java -jar freeki-vertx-server-0.4.1.jar -c /path/to/content-directory

Or, if you're using the `freeki` subdirectory in your home directory:

    java -jar freeki-vertx-server-0.4.1.jar

### Open a Browser, and Start Editing

Got to: [http://localhost:8080/wiki/](http://localhost:8080/wiki/) and get started!