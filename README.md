# psk-practice

1) Generated project with `mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-webapp -DarchetypeVersion=1.4`
2) Written mybatis generatorConfig... realised that this is probably database first. Dropped.
3) Written jpa hibernate entities and generated database tables code first.
4) Encountered `General error: "java.lang.IllegalStateException: Unable to read the page at position` Could not resolved it any other way than deleting db and recreating.
4) Back to mybatis, mvn mybatis-generator:generate