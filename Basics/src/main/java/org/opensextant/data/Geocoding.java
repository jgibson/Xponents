/**
 Copyright 2012-2013 The MITRE Corporation.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.


 * **************************************************************************
 *                          NOTICE
 * This software was produced for the U. S. Government under Contract No.
 * W15P7T-12-C-F600, and is subject to the Rights in Noncommercial Computer
 * Software and Noncommercial Computer Software Documentation Clause
 * 252.227-7014 (JUN 1995)
 *
 * (c) 2012 The MITRE Corporation. All Rights Reserved.
 * **************************************************************************
**/

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
//
// _____                                ____                     __                       __
///\  __`\                             /\  _`\                  /\ \__                   /\ \__
//\ \ \/\ \   _____      __     ___    \ \,\L\_\      __   __  _\ \ ,_\     __       ___ \ \ ,_\
// \ \ \ \ \ /\ '__`\  /'__`\ /' _ `\   \/_\__ \    /'__`\/\ \/'\\ \ \/   /'__`\   /' _ `\\ \ \/
//  \ \ \_\ \\ \ \L\ \/\  __/ /\ \/\ \    /\ \L\ \ /\  __/\/>  </ \ \ \_ /\ \L\.\_ /\ \/\ \\ \ \_
//   \ \_____\\ \ ,__/\ \____\\ \_\ \_\   \ `\____\\ \____\/\_/\_\ \ \__\\ \__/.\_\\ \_\ \_\\ \__\
//    \/_____/ \ \ \/  \/____/ \/_/\/_/    \/_____/ \/____/\//\/_/  \/__/ \/__/\/_/ \/_/\/_/ \/__/
//            \ \_\
//             \/_/
//
//  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
//

package org.opensextant.data;

/**
 *
 * @author Marc C. Ubaldino, MITRE, ubaldino at mitre dot org
 */
public interface Geocoding extends LatLon {

    //-------------------
    // High level flags: These attributes outline what this geocoding represents - a place, landmark, site, coordinate, etc.
    //-------------------
    /**
     * 
     * @return true if geocoding represents a named place
     */
    public boolean isPlace();

    /**
     * isCoordinate: if this object represents a coordinate
     * 
     * @return true if geocoding represents a coordinate
     */
    public boolean isCoordinate();

    /**
     * has Coordinate: if this named place object has a coordinate.
     * 
     * @return true if geocoding represents has a valid lat, lon
     */
    public boolean hasCoordinate();

    public boolean isCountry();

    public boolean isAdministrative();

    /**
     * Precision - radius in meters of possible error
     */
    public int getPrecision();

    /**
     * Precision - radius in meters of possible error
     */
    public void setPrecision(int m);

    /*
    public double getConfidence();
    */

    //---------------------
    // entity metadata:
    //---------------------
    public String getCountryCode();

    public void setCountryCode(String cc);

    public void setCountry(Country c);

    public String getAdmin1();

    public String getAdmin2();

    public String getAdminName();

    public String getAdmin1Name();

    public String getAdmin2Name();

    public String getFeatureClass();

    public String getFeatureCode();

    public String getPlaceID();

    public String getPlaceName();

    public void setPlaceName(String n);
    
    /** State-level postal code, the corresponds usually to ADM1 */
    public String getAdmin1PostalCode();
    
    /** City-level postal code, that may be something like a zip. 
     * Thinking world-wide, not everyone calls these zipcodes, as in the US. 
     */
    public String getPlacePostalCode();

    /**
     * @return Method for determining geocoding
     */
    public String getMethod();

    public void setMethod(String m);

}
