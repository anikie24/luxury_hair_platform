package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;

import java.util.Objects;
@Entity
public class Address {
    @Id
    private Long addressId;
    private int houseNumber;
    private String streetName;
    private String city;
    private int zipCode;

  //  @OneToMany(mappedBy = "address")
    //private List<Shipping> shippings;

    public Address() {
    }
    public Address(Builder builder){
        this.addressId = builder.addressId;
        this.houseNumber = builder.houseNumber;
        this.streetName = builder.streetName;
        this.city = builder.city;
        this.zipCode = builder.zipCode;
    }

    public Long getAddressId() {
        return addressId;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getCity() {
        return city;
    }

    public int getZipCode() {
        return zipCode;
    }

    //public List<Shipping> getShipping() { return shipping; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return houseNumber == address.houseNumber && zipCode == address.zipCode && Objects.equals(addressId, address.addressId) && Objects.equals(streetName, address.streetName) && Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, houseNumber, streetName, city, zipCode);
    }

    @Override
    public String toString() {
        return "AddressFactory{" +
                "addressId=" + addressId +
                ", houseNumber=" + houseNumber +
                ", streetName='" + streetName + '\'' +
                ", city='" + city + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }

    public static class Builder{
        private Long addressId;
        private int houseNumber;
        private String streetName;
        private String city;
        private int zipCode;

        public Builder setAddressId(Long addressId) {
            this.addressId = addressId;
            return this;
        }

        public Builder setHouseNumber(int houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public Builder setStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setZipCode(int zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder copy(Address address){
            this.addressId = address.addressId;
            this.houseNumber = address.houseNumber;
            this.streetName = address.streetName;
            this.city = address.city;
            this.zipCode = address.zipCode;
            return this;
        }
        public Address build(){
            return new Address(this);
        }
    }
}
