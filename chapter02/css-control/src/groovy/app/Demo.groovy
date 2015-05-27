package app

import app.tag.TagCloud
import app.tag.TagLabel
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI

@SuppressWarnings("serial")
public class Demo extends UI {
    @Override
    public void init(VaadinRequest request) {
        def names = ["HTML", "Java","Vaadin", "GWT", "CSS", "Javascript"]
        def fontSizes = [12,20,32,24,17,19]
        
        TagCloud tagCloud = new TagCloud()     
        for (int i=0 ; i < names.size ; i++){
            tagCloud.addComponent(new TagLabel(names[i], fontSizes[i]))
        }   
        
        tagCloud.setWidth(150, Unit.PIXELS)        
        setContent(tagCloud)
    }
}