package net.ramso.dita.repository;

import java.util.Map;

import net.ramso.dita.bookmap.Bookmap;
import net.ramso.dita.concept.Concept;
import net.ramso.dita.glosssentry.Glossentry;
import net.ramso.dita.glosssgroup.Glossgroup;
import net.ramso.dita.reference.Reference;
import net.ramso.dita.repository.content.DitaTools;
import net.ramso.dita.repository.content.DitaTypes;
import net.ramso.dita.repository.content.IndexException;
import net.ramso.dita.task.Task;
import net.ramso.dita.topic.Topic;

public interface IDitaContent {
	/**
	 * Unmarshall de jaxb object content in the iFile 
	 * @return the jaxb bean 
	 * @see Bookmap
	 * @see Concept
	 * @see Glossgroup
	 * @see Glossentry
	 * @see Map
	 * @see Reference
	 * @see Task
	 * @see Topic
	 * @see DitaTools
	 * @throws ContentException
	 * 
	 */
	Object getDocument()throws ContentException;
	/**
	 * Get the type of Dita document of the repository file 
	 * @see Bookmap
	 * @see Concept
	 * @see Glossgroup
	 * @see Glossentry
	 * @see Map
	 * @see Reference
	 * @see Task
	 * @see Topic
	 * @see DitaTools
	 * @return
	 * @throws ContentException
	 */
	DitaTypes getDitaType()throws ContentException;

	Map<String, String> getMetadata() throws ContentException;

	void addToIndex() throws ContentException, IndexException;

	void removeFromIndex() throws ContentException, IndexException;
}
