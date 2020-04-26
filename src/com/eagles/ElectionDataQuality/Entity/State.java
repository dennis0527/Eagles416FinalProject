package com.eagles.ElectionDataQuality.Entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(schema = "supaul", name = "STATE")
public class State {
    private String canonicalName;
    private String abbreviation;
    private String fullName;
    private String geojson;
    private Demographics demographicsByCanonicalName;
    private Collection<NationalPark> nationalParksByCanonicalName;
    private Precinct precinctByCanonicalName;
    private Coordinates coordinatesByCanonicalName;

    @Id
    @Column(name = "canonical_name", nullable = false, length = 45)
    public String getCanonicalName() {
        return canonicalName;
    }

    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }

    @Basic
    @Column(name = "abbreviation", nullable = true, length = 45)
    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Basic
    @Column(name = "full_name", nullable = true, length = 45)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "geojson", nullable = true)
    public String getGeojson() {
        return geojson;
    }

    public void setGeojson(String geojson) {
        this.geojson = geojson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(canonicalName, state.canonicalName) &&
                Objects.equals(abbreviation, state.abbreviation) &&
                Objects.equals(fullName, state.fullName) &&
                Objects.equals(geojson, state.geojson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(canonicalName, abbreviation, fullName, geojson);
    }

    @OneToOne(mappedBy = "stateByCanonicalAreaName")
    public Demographics getDemographicsByCanonicalName() {
        return demographicsByCanonicalName;
    }

    public void setDemographicsByCanonicalName(Demographics demographicsByCanonicalName) {
        this.demographicsByCanonicalName = demographicsByCanonicalName;
    }

    @OneToMany(mappedBy = "stateByCanonicalStateName")
    public Collection<NationalPark> getNationalParksByCanonicalName() {
        return nationalParksByCanonicalName;
    }

    public void setNationalParksByCanonicalName(Collection<NationalPark> nationalParksByCanonicalName) {
        this.nationalParksByCanonicalName = nationalParksByCanonicalName;
    }

    @OneToOne(mappedBy = "stateByCanonicalName")
    public Precinct getPrecinctByCanonicalName() {
        return precinctByCanonicalName;
    }

    public void setPrecinctByCanonicalName(Precinct precinctByCanonicalName) {
        this.precinctByCanonicalName = precinctByCanonicalName;
    }

    @OneToOne
    @JoinColumn(name = "canonical_name", referencedColumnName = "canonical_name", nullable = false, insertable=false, updatable=false)
    public Coordinates getCoordinatesByCanonicalName() {
        return coordinatesByCanonicalName;
    }

    public void setCoordinatesByCanonicalName(Coordinates coordinatesByCanonicalName) {
        this.coordinatesByCanonicalName = coordinatesByCanonicalName;
    }
}
