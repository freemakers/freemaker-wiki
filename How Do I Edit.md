<!-- Freeki metadata. Do not remove this section!
TITLE: How Do I Edit
-->
#How Do I Edit?

Obviously, this site claims to be a wiki. However, you might be asking yourself at this point, "How do I edit the content?"

The answer is that Freeki is actually a distributed wiki, with content synchronization happening through a GitHub project. To make changes, you need to checkout the wiki content to some local machine, then point your own copy of Freeki at it. After you get the content changes in place on your local system, you can push the changes up to the GitHub repository. From there, a service hook `POST`s to `http://www.freestatemakers.org/update/content` which triggers this server to pull down the changes.

Not only does this eliminate the need for complicated server-side authentication and user management, but it allows for offline editing of content. Best of all, anyone can contribute! All they need to do is fork the wiki content repository, make the changes they desire, push them back to GitHub, and create a pull request.

We have plans to streamline the contribution process further, but for now anyone familiar with GitHub should have no problems. 

For GitHub newbies, I'd suggest starting [here](https://help.github.com/articles/fork-a-repo).
