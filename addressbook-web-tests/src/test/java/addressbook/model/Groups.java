package addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> { //google guava library
    private Set<GroupData> delegate; //the object to which everything will be delegated

    public Groups(Groups groups) { //constructor that make copy of exist group object
        //take hashset of Groups groups as parameter and create new HashSet that consists the same elements to Set<GroupData> delegate
        this.delegate = new HashSet<GroupData>(groups.delegate);
    }

    public Groups() {
        this.delegate = new HashSet<GroupData>();
    }

    //from an arbitrary collection builds an object of type groups
    public Groups(Collection<GroupData> groups) {
        this.delegate = new HashSet<GroupData>(groups);
    }

    @Override
    protected Set<GroupData> delegate() {
        return delegate;
    }

    public Groups withAdded(GroupData group) {
        Groups groups = new Groups(this); //make copy of old group
        groups.add(group); //give the object as parameter
        return groups; //return copy with added group
    }

    public Groups without(GroupData group) {
        Groups groups = new Groups(this); //make copy of old group
        groups.remove(group); //give the object as parameter
        return groups; //return copy without group
    }

}
