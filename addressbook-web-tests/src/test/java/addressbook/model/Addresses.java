package addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Addresses extends ForwardingSet<AddressData> {
    private Set<AddressData> delegate;

    public Addresses(Addresses addressData) {
        this.delegate = new HashSet<AddressData>(addressData.delegate);
    }

    public Addresses() {
        this.delegate = new HashSet<AddressData>();
    }

    //from an arbitrary collection builds an object of type groups
    public Addresses(Collection<AddressData> addresses) {
        this.delegate = new HashSet<AddressData>(addresses);
    }

    @Override
    protected Set<AddressData> delegate() {
        return delegate;
    }

    public Addresses withAdded(AddressData address) {
        Addresses addresses = new Addresses(this);
        addresses.add(address);
        return addresses;
    }

    public Addresses without(AddressData address) {
        Addresses addresses = new Addresses(this);
        addresses.remove(address);
        return addresses;
    }

}