/**
 * Copyright 2014 The MITRE Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */

package org.opensextant.extractors.geo.rules;

import java.util.List;

import org.opensextant.data.Place;
import org.opensextant.extractors.geo.BoundaryObserver;
import org.opensextant.extractors.geo.LocationObserver;
import org.opensextant.extractors.geo.CountryObserver;
import org.opensextant.extractors.geo.PlaceCandidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GeocodeRule {

    public int weight = 0; /* of 10, approximately */
    public String NAME = null;

    protected CountryObserver countryObserver = null;
    protected LocationObserver coordObserver = null;
    protected BoundaryObserver boundaryObserver = null;
    protected Logger log = LoggerFactory.getLogger("geocode-rule");

    protected void log(String msg) {
        log.debug("{}: {}", NAME, msg);
    }

    protected void log(String msg, String val) {
        log.debug("{}: {} / value={}", NAME, msg, val);
    }

    public void setCountryObserver(CountryObserver o) {
        countryObserver = o;
    }

    public void setLocationObserver(LocationObserver o) {
        coordObserver = o;
    }

    public void setBoundaryObserver(BoundaryObserver o) {
        boundaryObserver = o;
    }

    public boolean sameCountry(Place p1, Place p2) {
        if (p1 == null || p2 == null) {
            return false;
        }
        if (p1.getCountryCode() == null || p2.getCountryCode() == null) {
            return false;
        }
        return p1.getCountryCode().equals(p2.getCountryCode());
    }

    /**
     *
     * @param names
     *            list of found place names
     */
    public void evaluate(List<PlaceCandidate> names) {
        for (PlaceCandidate name : names) {
            // Each rule must decide if iterating over name/geo combinations
            // contributes evidence. But can just as easily see if name.chosen is already
            // set and exit early.
            //
            /*
             * This was filtered out already so ignore.
             */
            if (name.isFilteredOut()) {
                continue;
            }
            // Some rules may choose early -- and that would prevent other rules
            // from adding evidence
            // In this scheme.
            if (name.getChosen() != null) {
                // DONE
                continue;
            }

            for (Place geo : name.getPlaces()) {
                if (filterOutBySize(name, geo)) {
                    continue;
                }
                evaluate(name, geo);
                if (name.getChosen() != null) {
                    // DONE
                    break;
                }
            }
        }
    }

    /**
     * Certain names appear often around the world... in such cases
     * we can pare back and evaluate only significant places (e.g., cities and states)
     * and avoid say streams and roadways by the same name.
     * 
     * If a name, N, occurs in more than 250 places, then consider only feature classes A and P.
     * 
     * @param name
     * @param geo
     * @return
     */
    protected boolean filterOutBySize(PlaceCandidate name, Place geo) {
        if (name.distinctLocationCount() > 250) {
            if (geo.isPopulated() || geo.isAdministrative()) {
                // allow P places and A boundaries to pass through.
                return false;
            }
            return true; // Filter out everything else.
        }

        // Okay, no optimization needed.
        return false;
    }

    /**
     * The one evaluation scheme that all rules must implement.
     * Given a single text match and a location, consider if the geo is a good geocoding
     * for the match.
     * 
     * @param name
     *            matched name in text
     * @param geo
     *            gazetteer entry or location
     */
    public abstract void evaluate(PlaceCandidate name, Place geo);

    /**
     * no-op, unless overriden.
     */
    public void reset() {

    }
}
