package app.revanced.patches.slopes.misc.premium

import app.revanced.patches.slopes.misc.premium.Fingerprints
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.bytecodePatch

@Suppress("unused")
val unlockPremiumPatch = bytecodePatch(
    name = "Unlock Premium",
) {
    compatibleWith("com.consumedbycode.slopes")

    execute {
        getCurrentSubscriptionType.method.addInstructions(
            0,
            """
                sget-object v0, Lcom/consumedbycode/slopes/vo/SubscriptionOrigin;->OTHER:Lcom/consumedbycode/slopes/vo/SubscriptionOrigin;

                return-object v0
            """,
        )

        getPassExpiration.method.addInstructions(
            0,
            """
                const-wide/32 v1, 0xbac9bd7cL

                invoke-static {v1, v2}, Ljava/time/Instant;->ofEpochSecond(J)Ljava/time/Instant;
                
                move-result-object v0

                return-object v0
            """,
        )

    }
}
