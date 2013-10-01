<!-- Freeki metadata. Do not remove this section!
TITLE: Attachments
-->
#Attachments

Currently, Freeki doesn't support attachments. Part of the reason I've held off on this is because Git doesn't do a good job of storing binary files. Each time you save an attachment with a particular name, the Git repository saves the old copy in its history. Over time, this can cause a TON of bloat.

However, the reality is that most wikis need to be able to attach images and other files that aren't "normal" wiki content.

## Pluggable Implementations?

It may be possible to implement several strategies for handling attachments. I can imagine a couple of approaches:

1. just bite the bullet and store them in git.
2. store them somewhere outside the git repository and provide a utility to sync that directory to someplace like Google Drive. 
    - find some way to prompt for sync, and automate the sync after some trigger (button press?) takes place.
    - then, cope with the URL changing relative to content when this sync happens.

## Attachments Endpoint

It's tempting to modify the static content resource to store/get attachments. However, attachments are by definition related to particular document...which is a relationship that the current static content resource known nothing about.

It makes more sense to implement a similar, but distinct, resource for handling attachments of some page within some group. This resource should support POST/GET/DELETE actions.

## Embedding Attachments In Git Repository

This is by far the simplest approach, and as long as the files don't see too many revisions and users keep a lid on the file sizes (*we could maybe put a restriction on this?*), it should be fine.

### Attachment Relationships on Disk

This leaves open the question of how to store attachments on disk in a way that they're obviously related to some page that's also on disk. We've gone to great lengths to avoid writing/maintaining a metadata file alongside the actual content. Part of the reason for this is how easily they could fall out of date when the content is edited by hand using a non-web text editor (it's just a directory of Markdown content, so this is a valid thing to do). In creating a directory for attachments, it seems that we will encounter this potential maintenance problem once again from a different angle.

### Embedded metadata: Attachment key

In our efforts to avoid storing a metadata file alongside the wiki content, we've added a metadata header to each page that consists of a comment (suppressed in rendered output) containing a set of keys and values. We could add an attachment key to this metadata which would be equal to the title of the page at the point when the related attachments directory is created. The attachments directory could then be named based on that title (`Page Name_attachments` maybe), and the wiki page's metadata could get an entry such as:

{{{
Attachments: Page Name_attachments
}}}
