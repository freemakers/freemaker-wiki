<!DOCTYPE html>
<html>
<% 
def last = '/wiki/'
def path = data.name.split('/')
def me = path.length > 0 ? path[path.length-1] : 'Wiki Root'
if ( path.length > 0 ){
  def newpath = new String[path.length-1]
  System.arraycopy(path,0,newpath,0,path.length-1)
  path = newpath
} %>
  <head>
  <title>${me}</title>
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
<div id="group-branding-header" class="branding-header">
  <span class="freeki-plug">Look, another <a target="_new" href="https://github.com/jdcasey/freeki">Freeki</a> portable wiki!</span>
</div>
<div id="group-header-panel">
  <span id="group-name"><% if (me == 'Wiki Root') { %>Wiki Root<% }else{ %>${data.name}<% } %></span>
</div>

<div id="group-breadcrumbs" class="breadcrumbs">
<% if(!data.name.equals("/")){ %><a class="breadcrumb-root breadcrumb" href="${last}">Wiki Root</a><span class="breadcrumb-sep">/</span><% path.each { last = last + it + '/' %> <a class="breadcrumb" href="${last}">${it}</a><span class="breadcrumb-sep">/</span><% }} %>
</div>

<div id="group-main" class="main-content">
<% if ( data.children ){ %>
<div id="group-listing">
  <ul>
  <% data.children.each { %>
    <li><a href="${it.id}<% if (it.type.name().equals("GROUP")) { %>/<% } %>">${it.label}<% if (it.type.name().equals("GROUP")) { %>/<% } %></a><% } %></li>
  </ul>
</div>
<% } %>

<footer class="group-footer">
  <div class="generated-on">
  <span style="font-size: small;">
    Generated on ${new Date()}.
  </span>
  </div>
<div class="freeki-branding-help"><span style="font-size: 8pt; float: left;"><b>NOTE:</b> You can turn these off by editing \$HOME/freeki/.branding/static/css/branding.css and adding:
<pre>
.freeki-branding-help, .freeki-plug{display:none;}
</pre>
</span>
</div>
</footer>

  <script type="text/javascript" src="/static/js/branding.js"></script>
  <script type="text/javascript" src="/static/js/group-extras.js"></script>
</body>
</html>