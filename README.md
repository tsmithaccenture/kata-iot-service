# kata-iot-service

# Install
1. 

# Kata
## Story
I want to turn on a remote light from a device
So that I can learn how basic IOT works

## Things You Need
1. Device w/
   1. WIFI
   1. SD Card
   1. Power Supply
1. I/O Add Ons
   1. button
   1. light
   
## Acceptance Tests
### Part 1 Add On:
Given that a button is in the off position
And the physical light is off
When I click the button to the on position
Then the physical light turns on

Given that a button is in the on position
And the physical light is on
When I click the button to the off position
Then the physical light turns off

### Part 2 Web:
Given that a button is in the off position
And the web page light image appears to be off
When I click the button to the on position
Then the web page light image appears to be on

Given that a button is in the on position
And the web page light image appears to be on
When I click the button to the off position
Then the web page light image appears to be off
