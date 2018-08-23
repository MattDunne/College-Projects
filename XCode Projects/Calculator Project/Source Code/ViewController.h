

#import <UIKit/UIKit.h>
#import "CalculatorModel.h"

@interface ViewController : UIViewController

@property (strong, nonatomic) IBOutlet UILabel *display;
- (IBAction)digitPressed:(id)sender;
- (IBAction)equalPressed:(id)sender;
- (IBAction)operationPressed:(id)sender;

@property BOOL userIsInTheMiddleOfEnteringNumber;
@property int val;
@property (strong, nonatomic) CalculatorModel *model;
@property (strong, nonatomic) NSString *result;
@property (strong, nonatomic) NSString *operand;
@property BOOL behindTheDecimal;

@end

