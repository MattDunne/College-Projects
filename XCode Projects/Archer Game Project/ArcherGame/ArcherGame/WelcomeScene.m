

#import "WelcomeScene.h"
#import "ArcheryScene.h"

@interface WelcomeScene ()
@property BOOL sceneCreated;
@end

@implementation WelcomeScene

- (void) didMoveToView:(SKView *)view {
    if (!self.sceneCreated) {
        self.backgroundColor = [SKColor greenColor];
        self.scaleMode = SKSceneScaleModeAspectFill;
        [self addChild: [self createWelcomeNode]];
        self.sceneCreated = YES;
    }
}

- (SKLabelNode *) createWelcomeNode {
    SKLabelNode *welcomeNode =
    [SKLabelNode labelNodeWithFontNamed:@"Bradley Hand"];
    
    welcomeNode.name = @"welcomeNode";
    welcomeNode.text = @"Tap Screen to Play";
    welcomeNode.fontSize = 24;
    welcomeNode.fontColor = [SKColor blackColor];
    
    welcomeNode.position =
    CGPointMake(CGRectGetMidX(self.frame), CGRectGetMidY(self.frame));
    
    return welcomeNode;
}

- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event {
    SKNode *welcomeNode = [self childNodeWithName:@"welcomeNode"];
    
    if (welcomeNode != nil)
    {
        SKAction *fadeAway = [SKAction fadeOutWithDuration:1.0];
        
        [welcomeNode runAction:fadeAway completion:^{
            SKScene *archeryScene =
            [[ArcheryScene alloc]initWithSize:self.size];
            
            SKTransition *doors =
            [SKTransition doorwayWithDuration:1.0];
            
            [self.view presentScene:archeryScene transition:doors];
        }];
    }
}

@end
