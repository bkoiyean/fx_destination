# Overview of FX Destination: an E-commerce FX trading website
<a href="http://www.youtube.com/watch?feature=player_embedded&v=8gO7-6R1tSs&t=7s
" target="_blank"><img src="https://github.com/bkoiyean/fx_destination/blob/main/Screen%20Shot%20Overview%20membership.png" 
alt="Overview" width="800" height="500" border="10" /></a>
- Business: sell foreign currencies (FX) online to Australia customers.
- Same business in market: First Eastern FX, Travelex, OZMoney.
- Outstanding services provided by FX Destination:
  + Membership benefits: the more you buy, the higher rate you get with 5 levels: 10K, 20K, 50K, 100K, 200K.
  + Guaranteed rate in 6 hours: your order is guaranteed with the highest rate in 6 hours since it is paid. It means if the rate increases within 6 hours from the time you make an order, you will get the new rate.
  + Rate tracker: tell us your expected rate and you will be notified when it reaches your target.
  + Rate monitor:  our rate graph history for each currency will help you decide when you should buy.
  + Market overview: easily find current rates offered by other companies in Australia and commercial market in the world.

# Some features of the website
1. Filter: User can’t access product & tools page without login
2. Register:
- JavaScript to require strong password and validate it in real-time (length of minimum 8 characters, UPPER, lower, number & special character)
<a href="http://www.youtube.com/watch?feature=player_embedded&v=8gO7-6R1tSs&t=22s
" target="_blank"><img src="https://github.com/bkoiyean/fx_destination/blob/main/Screen%20Shot%20Validate%20password.png" 
alt="Validate password" width="800" height="500" border="10" /></a>
- Use Bcrypt with Blowfish encryption algorithm (128-bit salt -> 192-bit magic value) to encrypt password:
  + Identical passwords will be generated in different secret codes.
  + Data leak will not cause password leak.
3. Login:
- Alternative OTP login:
<a href="http://www.youtube.com/watch?feature=player_embedded&v=8gO7-6R1tSs&t=40s
" target="_blank"><img src="https://github.com/bkoiyean/fx_destination/blob/main/Screen%20Shot%20OTP%20login.png" 
alt="Alternative OTP login" width="800" height="500" border="10" /></a>
  + Users enter correct email address.
  + Users receive via email a 6-digit code (OTP) which is valid in 1 minute only.
  + Users enter OTP to login.
- Forgot password:
<a href="http://www.youtube.com/watch?feature=player_embedded&v=8gO7-6R1tSs&t=1m40s
" target="_blank"><img src="https://github.com/bkoiyean/fx_destination/blob/main/Screen%20Shot%20Forgot%20password%20function.png" 
alt="Alternative OTP login" width="800" height="500" border="10" /></a>
  + Users enter correct email address.
  + Users receive via email an unique link including 36 characters (128-bit: 32 hex digits + 4 -) generated by UUID (Universally Unique Identifier) which is valid in 24 hours AND valid in 5 minutes since it is clicked.
  + Users must create new password within 5 minutes.
  + A new password is reset.
  + Users enter the new password to login.
4. Dashboard:
- Update details function:
  + Hiden for sensitive details.
  + Llexible for password update.
- Show level of membership + threshold of next level:
  + Different rates for different accounts in product page.
  + Purchase large amount to increase level.
- Show credit amount thanks to rate guarantee policy:
  + Different amount for different accounts to pay in cart page.
- Show dynamic message: pending order if added to cart but not paid; number of order paid today…
- Show pending rate tracker: show the highest target for each rate if multiple same rate tracker is applied.
- Show Order stats: total AUD paid -> decide level of membership + total amount of each currency ordered.
- Show the latest order details.
5. Product page:
- JavaScript to show different photos + denomination depending on which currency users select:
  + Not selected: around the world.
  + Selected: show photos from country + denomination of selected currency.
- JavaScript to automate conversion and allocation of denomination:
  + Users enter FX amount -> AUD amount & quantity of each note will be auto updated.
  + Users enter quantity of note -> FX amount & AUD amount will be auto updated.
  + Users enter AUD amount -> FX amount & quantity of note will be auto updated.
- JavaScript to show Add to Cart only when valid amount entered:
- JavaScript to disable currencies which were already added to cart:
6. Cart page:
- Remove currency function: demo
- Integrated card payment service from Stripe:
  + Success: 4000000360000006
  + Declined: 4000000000000002
  + Insufficient funds: 4000000000009995
  + Stolen card: 4000000000009979
7. Track order:
- Disable if users haven't logged in yet:
8. Locations with Google Map: 
- Demo
9. Contact:
- Demo different email under session
10. Tools:
- Graph history: based on local database
- Market overview:
- Rate Tracker:
- Rate Guarantee:
