package net.ramso.dita.jsf.components;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import net.ramso.dita.content.ContentFactory;

@ManagedBean
public class ContentAccordion {
     
    private List<ContentTree> contentTrees;
 
    @PostConstruct
    public void init() {
        contentTrees = new ArrayList<ContentTree>();
        contentTrees.add(new ContentTree(ContentFactory.PROJECTLABEL));
        contentTrees.add(new ContentTree(ContentFactory.TEMPLATESLABEL));
        contentTrees.add(new ContentTree(ContentFactory.COMPONENTSLABEL));
    }
     
    public List<ContentTree> getContentTrees() {
        return contentTrees;
    }
     
    
}