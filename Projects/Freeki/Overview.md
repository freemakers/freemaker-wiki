<!-- Freeki metadata. Do not remove this section!
TITLE: Project Overview
-->
#Project Overview

Freeki is a wiki system designed from the ground up to be as minimal as possible in terms of the server that runs it. The application started out as a training exercise to learn the Vert.x Java API, and turned into a fun challenge to see how much functionality I could add to something so small.

## Features

### Markdown, Natively

Documents are written and stored in [Markdown][1] format. They are rendered to HTML on the fly or via server-side component, depending on the runtime mode of the server (read-only vs. normal). 

By default, documents are edited using the [Pagedown][2] javascript Markdown editor.

### Git for History and Sharing

Freeki's content store is fundamentally a [Git][3] repository. If you point Freeki at a non-existent or new directory, it will even initialize a new Git repository in that location.

All document modifications are stored as revisions in the Git repository. These revisions can be pushed to a remote Git repository, shared via `format-patches`, cloned to new Git repositories to form the foundation of a new wiki...everything you already love about Git.

### Git + REST = Content Synchronization

Additionally, Freeki exposes a REST endpoint that can 'listen' for a Git repository hook (an action taken in response to some event like a new commit, new push, etc.). Using this endpoint, editors can push their changes up to a centralized Git server (like we've done for [this wiki][4]), and configure GitHub to notify a centralized server for your content. When the push completes, GitHub will notify the configured URL, and the listening Freeki server will pull the changes down from GitHub.

This update process is actually what drives content updates on this site!

### Branding (Skinning) Enabled

Your basic Freeki wiki is enhanced with jQuery-enabled javascript that introduces WSIWYG editor features. 

However, Freeki also supports a branding directory that is wiki-specific. This branding directory contains any custom CSS, images, wiki templates (see below), javascript, or even Groovy page templates. By default this directory is in your wiki's content Git repository, under the path: `<content-root>/.branding`. 

Modifying the files in your branding directory allows you to take almost total control over how the content is created and displayed. Everything from simple CSS overrides and logo introduction, all the way up to changing the page structure itself, is possible from this directory.

The branding directory can also be configured to any location on the filesystem, in case you want to override the default skin for the content with something unique to a particular Freeki instance.

### Content Templating

Freeki allows you to provide your own templates for creating new content. Templates each consist of two files: one for the HTML form used to collect template parameters, and another to generate the new content and redirect the user on completion. 

Content templates can create any number of new groups or pages, allowing you to generate a whole new content structure when your template runs.

### Read-Only Mode

Freeki now supports a read-only mode (as of the 0.4 release), where all editors are disabled and POST/PUT/DELETE requests are denied. While it's possible to achieve this using branding directory customization (see above), read-only mode is designed to work with minimal duplication of branding resources.

Read-only mode enables hosting the wiki as a read-only website with the simple addition of a single command-line configuration (`-r`).

## Technology

Freeki is driven by a small collection of APIs:

- [Vert.x](http://vertx.io/) provides the HTTP framework
- [Groovy](http://groovy.codehaus.org/) provides the foundations for the templating engine used to render the basic page structures.
- [Gson](http://code.google.com/p/google-gson/) provides the API for customized `object <-> JSON` translation, for talking to the Javascript user interface via [REST](http://en.wikipedia.org/wiki/Representational_state_transfer) services.
- [jQuery](http://jquery.com/) is used to make the Javascript UI easier to manage.
- [jGit](http://www.eclipse.org/jgit/) is used to store content revisions in the wiki Git repository.


  [1]: http://daringfireball.net/projects/markdown/syntax
  [2]: http://code.google.com/p/pagedown/
  [3]: http://git-scm.com/
  [4]: https://github.com/jdcasey/freemaker-wiki