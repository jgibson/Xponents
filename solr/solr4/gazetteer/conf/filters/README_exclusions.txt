Exclusions of Placenames Homonyms
---------------------------------

There are enough words in common language that coincide with names of places that 
we have to make some practical choices about limiting the scope of phrase tagging.
The list of "non-places.csv" captures those common dictionary and other terms
that appear often and in almost all cases seen empirically, those terms are not 
referring to a location of any sort in context.

This list is used to mark gazetteer entries as search_only.
There is still opportunity to post-filter phrases that are found in the tagging phase.
Just as there is the option to re-tag a document ignoring the "search_only" criteria.


Most of these non-place names are marked with name_bias = 0 which does not distinguish 
them from other valid place names with the name_bias = 0.


Middle Eastern titles were derived from various sources including:

 http://heraldry.sca.org/laurel/names/arabic-naming2.htm

WordNet stop words were derived from Ted Pedersen, 
   http://www.d.umn.edu/~tpederse/Group01/WordNet/words.txt


US Census 2010 (http://census.gov) supplied the surnames data.
http://www.census.gov/topics/population/genealogy/data/1990_census/1990_census_namefiles.html
First names are located at:
  http://www2.census.gov/topics/genealogy/1990surnames/dist.male.first
  http://www2.census.gov/topics/genealogy/1990surnames/dist.female.first


Build script (build.sh) is default build for gazetteer.  Put US Census files above  in 
./conf/filters/census/ .  The Python script ./script/assemble_person_filter.py handles
these files to put together a final name list.
