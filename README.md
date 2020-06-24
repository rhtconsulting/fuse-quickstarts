# Red Hat Fuse Quick Starts

This project contains multiple Quick Starts intended for use with Red Hat Fuse. These Quick Starts are intended to be beginner level projects that demonstrate a single concept at a time. In some cases more Advanced Quick Starts are included that build upon the basic Quick Start to demonstrate more complex features. The Quick Starts are designed to get someone started quickly with a given component and are not designed to be full solutions. Quick Starts are provided for both Fuse on Karaf as well as Fuse on EAP.

 * [Red Hat Fuse on Karaf Examples](karaf)
 * [Red Hat Fuse on EAP Examples](eap)
 * [Red Hat Fuse on Spring Boot Examples](spring-boot)

If you are looking for more complex solutions or larger examples, please see the [FuseByExample](https://github.com/FuseByExample) GitHub account.

## Contributing a Quick Start ##
If you have a Red Hat Fuse Quick Start that you would like to see added here, please feel free to contribute! Just fork this repo, add your Quick Start to it, and submit a Pull Request to have it added. Once it has been verified to meet the standards listed below, it will be merged in. It's that easy!

If you don't have anything of your own and still want to help you can see the Quick Starts that have been requested [here](https://github.com/rhtconsulting/fuse-quickstarts/issues?q=is%3Aopen+is%3Aissue+label%3A%22example+request%22).

### Quick Start Standards ###
In order to keep the Quick Starts working and useful, there are some standards that all Quick Starts should adhere to.

- **Blueprint over Spring**
  - When developing Quick Starts, favor Blueprint XML over Spring DM. Blueprint XML is the recommended approach when using Karaf and the Spring DM framework is no longer maintained.
- **Java DSL over XML**
  - Use the Java DSL over XML based routes whenever possible as beginners generally find it easier to understand than the XML when coming from a Java background.
- **Provide Automated Tests**
  - Add Unit and Integration tests when appropriate.
- **Demonstrate both Sides of the Problem**
  - Quick Starts should demonstrate both the Producer and Consumer of the component when appropriate.
- **Provide Clear Instructions**
  - A `README.md` should be provided with requirements, set up steps, steps to run, and expected output. Additional explanations can be provided as needed.
- **Keep it Simple**
  - Quick Starts should be kept as simple as possible. In most cases, this means not much more complex than the examples found in the documentation. This is to ensure that they are easily understood by developers at all levels. Quick Starts that are inherently more complex should be noted.
- **Follow Maven Best Practices**
  - All Quick Starts should use the relevant Parent POM's that already exist within the project and update them with new dependencies as needed.
