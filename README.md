# JBoss Fuse Quick Starts

This project contains multiple Quick Starts intended for use with JBoss Fuse. These Quick starts are intended to be beginner level projects that demonstrate a single concept at a time. In some cases more Advanced Quick Starts are included that build upon the basic Quick Start to demonstrate more complex features. The quickstarts are designed to get someone started quickly with a given component and are not designed to be full solutions. Quick starts are provided for both Fuse on Karaf as well as Fuse on EAP.

 * [JBoss Fuse on Karaf Examples](https://github.com/rhtconsulting/fuse-quickstarts/tree/jboss-fuse-6.3/karaf)
 * [JBoss Fuse on EAP Exampples](https://github.com/rhtconsulting/fuse-quickstarts/tree/jboss-fuse-6.3/eap)

If you are looking for more complex solutions or larger examples, please see the [FuseByExample](https://github.com/FuseByExample) GitHub account.

## Contributing a Quickstart ##
If you have a JBoss Fuse quickstart that you would like to see added here, please feel free to contribute! Just checkout this repo, add your quickstart to it, and submit a Pull request to have it added. Once it is verified that it meets the standards below, it will be merged in. Its that easy!

If you dont have anything of your own and still want to help you can see the Quickstarts that have been requested [Here](https://github.com/rhtconsulting/fuse-quickstarts/issues?q=is%3Aopen+is%3Aissue+label%3A%22example+request%22).

### Quickstart Standards ###
In order to keep the Quickstarts working and useful, there are some standards that all quickstarts should adhere too.

- **Blueprint over Spring**
 - When developing your quickstarts you should favor Blueprint XML over Spring DM. Blueprint XML is the recommended approach when using Karaf and the Spring DM framework is no longer maintained.
- **Java DSL over XML**
 - Use the Java DSL over XML based routes whenever possible as beginners generally find it easier to understand than the XML when coming from a Java background.
- **Provide Automated Tests**
 - Add Unit and Integration tests when appropriate.
- **Demonstrate both Sides of the Problem**
 - Quickstarts should demonstrate both the Producer and Consumer of the component when appropriate.
- **Provide Clear Instructions**
 - A README should be provided with requirements, setup, steps to run, and expected output. Additional explanations can be provided as needed.
- **Keep it Simple**
 - Quickstarts should be kept as simple as possible. In most cases this means not much more complex than the examples found in the documentation. This is to ensure that they are easily understood by developers at all levels. Quickstarts that are inhertently more complex should be noted.
- **Follow Maven Best Practices**
 - All Quickstarts should use the relevant Parent POM's that already exist within the project and update them with new dependencies as needed.
