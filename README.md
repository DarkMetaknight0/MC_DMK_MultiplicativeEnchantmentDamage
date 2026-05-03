Mod Info:
=======
Changes Sharpness, Smite, Bane of Arthropods, and Impaling damage calculations to be percentage based. This mod aims to
lessen the gap between enchanted heavy vs light weapons.

Mods often add "fast and weak" vs "slow and powerful" and even 2-handed weapons. When the additional damage from
enchantments is all linear, the balance falls apart and fast weapons almost always win out in DPS. Because of the flat
additions, the % DPS increase for 1 handed is much greater than 2 handed due to faster attack rate.

This mod changes the following enchantment damage modifiers (Based on Vanilla scaling at 6 damage from a lighter weapon,
Knives):

1. Sharpness: From +1 damage at level 1, 0.5 per additional level -> +16.8% level 1, + 8.3% per additional. (x1.5 at
   level 5)
2. Bane of Arthropods, Smite: +53% level 1, +53% per additional level. (x2.66 base damage at level 5 vs specific mobs)

Fully configurable, so you can fine tune the balance:

1. Whether to run the multiplier logic at all for this enchant.
2. Whether to override the default enchantment behavior (undo flat additions)
3. What the first level multiplier is.
4. What each additional level adds to that multiplier.

Keep in mind this stacks on top of crits (e.g. 1.5x from crit times 2.65x)

Installation information (Template)
=======

This template repository can be directly cloned to get you started with a new
mod. Simply create a new repository cloned from this one, by following the
instructions provided
by [GitHub](https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template).

Once you have your clone, simply open the repository in the IDE of your choice. The usual recommendation for an IDE is
either IntelliJ IDEA or Eclipse.

If at any point you are missing libraries in your IDE, or you've run into problems you can
run `gradlew --refresh-dependencies` to refresh the local cache. `gradlew clean` to reset everything
{this does not affect your code} and then start the process again.

Mapping Names:
============
By default, the MDK is configured to use the official mapping names from Mojang for methods and fields
in the Minecraft codebase. These names are covered by a specific license. All modders should be aware of this
license. For the latest license text, refer to the mapping file itself, or the reference copy here:
https://github.com/NeoForged/NeoForm/blob/main/Mojang.md

Additional Resources:
==========
Community Documentation: https://docs.neoforged.net/  
NeoForged Discord: https://discord.neoforged.net/
