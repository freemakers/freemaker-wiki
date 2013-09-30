import org.commonjava.freeki.model.*;
import org.commonjava.freeki.data.*;

public class NewProjectAction implements TemplateAction {
  public String run( Map<String, String> params, FreekiStore store ){
      System.out.println("Creating project: " + params.get("project"))
      
      def name = params.get("project")
      def group = "Projects/${name}"
      
      def descPg = """\
# Project Overview

*Enter a description for your project here.*
      """
      
      def tstamp = System.currentTimeMillis();
      
      store.storePage( new Page( group, "Overview", descPg, "Project Overview", tstamp, null ) );
      
      return "/wiki/${group}/"
  }
}