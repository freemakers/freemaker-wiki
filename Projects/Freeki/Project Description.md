<!-- Freeki metadata. Do not remove this section!
TITLE: Project Description
-->
#Project Description

Freeki is a wiki system designed from the ground up to be as minimal as possible in terms of the server that runs it. The application started out as a training exercise to learn the Vert.x Java API, and turned into a fun challenge to see how much functionality I could add to something so small.

## Features

- Documents are written in [Markdown](http://daringfireball.net/projects/markdown/syntax) format, and edited via javascript-driven Markdown editor
- Documents modifications are stored on the filesystem as revisions in a [Git](http://git-scm.com/) repository, enabling decentralized modification, forking of content, and so on.
- The basic wiki is enhanced with jQuery-enabled javascript to introduce WSIWYG editor features, or whatever else you want
- Javascript, CSS, images, and more can be 'skinned' uniquely for each wiki using a branding directory embedded in the Git repository used to store wiki content.
- The wiki server supports a read-only mode, where all editors are disabled and POST/PUT/DELETE requests are denied. This enables publishing the wiki as a website with the addition of only one command-line configuration. 

## Technology

Freeki is driven by a small collection of APIs:

- [Vert.x](http://vertx.io/) provides the HTTP framework
- [Groovy](http://groovy.codehaus.org/) provides the foundations for the templating engine used to render the basic page structures.
- [Gson](http://code.google.com/p/google-gson/) provides the API for customized `object <-> JSON` translation, for talking to the Javascript user interface via [REST](http://en.wikipedia.org/wiki/Representational_state_transfer) services.
- [jQuery](http://jquery.com/) is used to make the Javascript UI easier to manage.
- [jGit](http://www.eclipse.org/jgit/) is used to store content revisions in the wiki Git repository.
