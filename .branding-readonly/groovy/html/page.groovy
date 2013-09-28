<!DOCTYPE html>
<html>
  <head>
    <title>${data.title}</title>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="/static/js/Markdown.Converter.js"></script>
    <script type="text/javascript" src="/static/js/Markdown.Sanitizer.js"></script>
    <script type="text/javascript" src="/static/js/Markdown.Editor.js"></script>
    
    <link rel="stylesheet" href="/static/css/jquery-ui.css"/>
    <link rel="stylesheet" href="/static/css/pagedown.css"/>
    <link rel="stylesheet" href="/static/css/branding.css"/>
  </head>
  <body>
<div id="page-branding-header" class="branding-header">
  <span id="freeki-plug">Look, another <a target="_new" href="https://github.com/jdcasey/freeki">Freeki</a> portable wiki!</span>
</div>
<div id="page-breadcrumbs" class="breadcrumbs">
<% def last = '/wiki/' %>
<a class="breadcrumb-root breadcrumb" href="${last}">Wiki Root</a><span class="breadcrumb-sep">/</span><% data.group.split('/').each { last = last + it + '/' %> <a class="breadcrumb" href="${last}">${it}</a><span class="breadcrumb-sep">/</span><% } %>
</div>
<div id="page-main" class="main-content">
<div id="page-content">
</div>
</div>

  <footer id="page-footer">
    <div class="generated-on">
      <span style="font-size: small;">Last updated: ${data.updated} by ${data.currentAuthor}.</span>
    </div>
    <div class="freeki-branding-help">
      <span style="font-size: 8pt; float: left;"><b>NOTE:</b> You can turn these off by editing \$HOME/freeki/.branding/static/css/branding.css and adding:
<pre>
.freeki-branding-help, .freeki-plug{display:none;}
</pre>
      </span>
    </div>
  </footer>

  <script type="text/javascript" src="/static/js/wikiMain.js"></script>
  <script>
    \$(document).ready(function(){
      init("${data.content}");
    });
  </script>
  <script type="text/javascript" src="/static/js/branding.js"></script>
  <script type="text/javascript" src="/static/js/page-extras.js"></script>
</body>
</html>