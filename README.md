# JBoss Fuse Quick Starts

This project contains multiple Quick Starts intended for use with JBoss Fuse. These Quick starts are intended to be very basic beginner level projects that demonstrate a single concept at a time. In some cases more Advanced Quick Starts are included that build upon the basic Quick Start to demonstrate more complex features. All of these are designed to get someone started quickly with a given component and are not designed to be full solutions. Quick starts are provided for both Fuse on Karaf as well as Fuse on EAP.

 * [JBoss Fuse on Karaf Examples](https://github.com/rhtconsulting/fuse-quickstarts/tree/master/karaf)
 * [JBoss Fuse on EAP Exampples](https://github.com/rhtconsulting/fuse-quickstarts/tree/master/eap)

If you are looking for more complex solutions or larger examples, please see the [FuseByExample GitHub](https://github.com/FuseByExample) account.

## Contributing a Quickstart ##
If you are a Red Hat Consultant, Architect, or otherwise involved with Red Hat Consulting and have a JBoss Fuse quickstart that you would like to see added here, please feel free to contribute! Just checkout this repo, add your quickstart to it, and submit a Pull request to have it added. Once it is verified that it meets the standards below, it will be merged in. Its that easy!

### Quickstart Standards ###
The following standards should be followed when contributing a new quickstart to the project.

- Always prefer Blueprint XML Configuration over Spring DM.
- Always prefer Java DSL over XML based routes.
- Add Unit and Integration tests when appropriate.
- Quickstarts should demonstrate both the Producer and Consumer of the component when appropriate.
- A README should be provided with quickstart requirements, setup, steps to run, and expect output. Additional explanation can be provided as needed.
- Quickstarts should be at a level above the code samples found in the official documentation but need not be a full solution.
- All Quickstarts should use the relevant Parent POM that already exists within the project.
