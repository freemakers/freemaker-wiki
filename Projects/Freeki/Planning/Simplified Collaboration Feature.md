<!-- Freeki metadata. Do not remove this section!
TITLE: Simplified Collaboration Feature
-->
#Simplified Collaboration Feature

If you're comfortable with Java and GitHub, it's relatively simple to fork the Git repository backing this website, setup your own local Freeki instance, edit the content, push your changes to your fork, and create a pull request. 

[Here][1] are the steps in more detail.

If you read the link above, it will be obvious that there's room for improvement. There are at least two good reasons that this isn't simple enough:

1. Not everyone is a Java + GitHub expert, and they shouldn't have to be in order to contribute.

2. While it's not too difficult, it's still a long way around to get your content sync'ed to the server. It can be more automated, and therefore it should be.

## Pushing Edits

GitHub has an API for creating forks and pull requests. jGit has support for both pull and push operations. Using a REST endpoint, we should be able to support something like a "Push" button on the local editable instance, which would:

1. Push up to GitHub
    1. As a side effect, any configured hooks get fired, which (for this wiki at least) would ping the view-mode wiki server to update its content. (**WORKS as of 2013-10-02**; using Push Updates button on an editable wiki.)
2. Maybe also fire off the GH create-pull-request operation 
    1. We probably want to think about making this optional in case we want to decouple from GH

## Forking

We should also look into generating a JNLP (Java Web Start) file tuned to a particular wiki's origin git repository, to support putting a link on the site to spawn an editable instance on the localhost. 

This would:

1. Prompt for GH username
2. Fork the wiki's origin repository using the github api
3. Prompt for local directory for the forked git repository clone
4. Clone the repository to the local directory
5. Start a local, editable Freeki instance pointed at the freshly cloned repository

  [1]: /wiki/Projects/Freeki/Run%20Your%20Own%20Copy#
