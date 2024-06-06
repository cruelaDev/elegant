Autehntication dan o'tish uchun:
1. UserControllerdagi "auth/sign-up" apiga request yuboriladi. Shu bilan kiritilgan emailga confirmation code boradi.
2. EmailControllerdagi "auth/verification" apiga emailga kelgan code bilan birga request yuboriladi va user databasega saqlanadi.
3. UserControllerdagi "auth/sign-in" apiga request yuboriladi. Responseda JwtToken qaytariladi va shu token orqali user permissionlarga ega bo'ladi.
 

