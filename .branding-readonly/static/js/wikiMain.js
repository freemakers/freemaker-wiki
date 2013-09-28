function init( pageContent ){
  converter = new Markdown.Converter();

  var rendered = converter.makeHtml(pageContent);
  $('#page-content').html(rendered);
}

