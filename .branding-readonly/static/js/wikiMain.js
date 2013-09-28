function init(){
  var pageContent = $('#page-content');
  if (pageContent){
    var converter = new Markdown.Converter();

    var rendered = converter.makeHtml($(pageContent).text());
    $(pageContent).html(rendered);
  }
}

