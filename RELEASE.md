#2016#

* Xponents 2.9.0 
 - Fresh look at how resource files are pulled from CLASSPATH:  InputStream (getResourceAsStream()) is the primary entry point to 
   pulling in any sort of config file or data resource.  Getting File or URL should be left to the caller of APIs. If such things
   are offered in these APIs it is for mere convenience.  Pulling items from JAR, file system, CLASSPATH, etc. seem to behave differently
   in different environemnts: e.g., HDFS, Server vs. Client Applications, etc.

 - Solr 4.x refactoring:  Provision Solr from Jetty v9; No longer using the crippled jetty-runner v8 JAR.

 - Patterns: Streamlined constructors given the resource file issue at top

 - Extraction: PlaceGeocoder now weights findings against explictly mentioned countries to improve disambiguation.  About 1% improvement in F-score.

* Xponents 2.8.18
 - Extraction: NonsenseFilter added short name + number pattern to filter out unlikely name match, 
   that is aimed at rare gazetteer entries. Alternatively, mark such things in Gazetteer as SearchOnly = true (to avoid tagging at all, by default)

* Xponents 2.8.17
 - Basics:  US State metadata for mapping FIPS/ISO and ADM1/Postal code pairings
 - Extraction: NonsenseFilter added to deal with odd punctuation situations as a result of over-tagging or deep tokenization.

* Xponents 2.8.16
 - Basics:  Country data improvements include territories, timezones, languages spoken, etc; Backed by GeonamesUtility and GeodeticUtility
 - Basics:  Place object is fleshed out more with population data, when available;  ASCII and other name hueristic flags; Geohash options; 
   overall improved Geocoding interface; Backed by techniques in TexUtils
 - Patterns: XCoord and GeocoordMatch reimplement Geocoding interface; Date/Time pattern fixes
 - Extraction: PlaceGeocoder added support for Arabic and CJK text parsing if given a language ID; 
   Refactored rules stack and performance on scoring candidate names.  Overall improvement in default score for a place match;
   Tweaked JRCNames to allow for better false-positive negation.
 - XText 2.9.x:  Tika 1.13 upgrade; Improved Web/Sharepoint crawling logic (not perfect). Allows user to filter links worth capturing and converting
 - XText 2.9.x:  TikaHTML parser/converter was not yielding reasonably obvious metadata tags (title, org, author, etc.) so I pulled in JerichoHTML to get tags.

   
#2015#
* Xponents 2.8.5 - december 2015: 
 - adding timezone and language metadata; 
 - PlaceGeocoder: rules and tracing improved.
 - PlaceGeocoder: Added nationality detection using XTax; inferred countries lightly rank candidates higher.
* Xponents 2.8.x - november 2015: Long over due refactor
 - Extraction/Geo: PlaceGeocoder now emitting reasonable choice for location of names; Still initial draft. Heavily involved in rules development in Java here.  Evaluation of these features is still very much a personal/internal thing. 
   -- TODO: document rules in plain language
   -- TOOD: someday opensource evaluation tools
 - Patterns (*new*): Splintered off FlexPat-based libraries into this new module. If all a user wants is regex style patterns, they do not need Tika or Solr or any of that.
 - Basics: TextUtils now has more text case checking tools
 - MOVES:
   --  Basics 'flexpat' ---> Patterns
   --  Extraction 'xcoord','xtemporal','poli' --> Patterns
 
* Xponents 2.7.19 - november 2015, bug fixes and fine tuning .16 patches
* Xponents 2.7.16 - october 2015
 - Extraction: 'PlaceGeocoder' saw a focused effort on improving how popular well-known 
   entities can be used to negate gazetteer tagging. This solution makes better use of XTax as a naiive entity tagger.
   Overall, recall is maximized at the same time geo-tagging precision is maximized.
   As well, the foundation of "Geocode Rules" is established but needs further documentation.

* Xponents 2.7.15 - october 2015
 - Java 8: tested strict javadoc compilation and fixed errors.  Warnings remain
 - Basics: added timezone/UTC offset table to country objects (courtesty of geonames.org)
 - packaging: removed deprecated code such as progress listeners
 - Extraction: Retested Gazetteer spatial query, as certain standard solr spatial mechanisms force index to load into RAM, e.g., sort-by-dist
 - Extraction: lower-case and case-insensitive matching enabled in GazetteerMatcher for odd cases like working with social media
 - Extraction/Gazetteer:   added abillity to upload JSON form of gazetteer records, e.g. aliases for existing known gazetter entries
   
* Xponents 2.7.8 - july 2015
 - Java 7 is the norm, but tested compilation and running on Java 8.
 - XText: improved semantics for found hyperlinks in web crawls
 - XText: Tika 1.8 is latest
 - Basics: fixed country code hash maps; added more text utility for handling unicode situations: Emojis and other language issues.
 - Basics: Enhanced the concept of a "Geocoding"  interface to include ADM1 Name in addition to ADM1 code
 - Extraction: Honed use of JRCNames as a keyword tagging resource in XTax
 - Extraction: Devised a rule set for a full range of geocoding ideas in PlaceGeocoder (coords, countries, places) while looking at filtering out terms and tokens for performance reasons.
 - Dist: Improved distribution packaging (script/dist.xml)
   

#2014#

* Xponents 2.5.1 - July 2014 
 - Java 7+ required now;  Java 6 source syntax supported, but release will be Java 7 binary
 - Javadoc cleanup
 - XText refactor, given added archive file support; concept of caching and crawling is optional and moved out of main conversion logic. 

* Xponents 2.4.3 - June 2014
 - Extraction: MGRS filters for well known dates/months, lower case (default is to filter out lowercase), and Line endings in Latband/GZD
 - XText bug fixes; check style review:  v1.5.4
 - POM cleanup and indentation; review unspecified compile time dependencies

*  Xponents 2.3  - May 2014
 - minor tweeks in APIs
 - added set_match_id(match, counter)  to FlexPat matchers

*  Xponents XText 1.5 - May 2014
 - numerous fixes in XText proper, and many path normalization fixes in ConvertedDocument
 - added Mail crawler and MessageConverter for handling email
 - many improvements to JPEG/EXIF conversion
