#import <UIKit/UIKit.h>

@interface TableViewController : UITableViewController <UIPageViewControllerDataSource, UIPageViewControllerDelegate>

@property (strong, nonatomic) UIPageViewController *pageViewController;
@property (strong, nonatomic) NSArray *pageData;
@property (strong, nonatomic) NSArray *Descrip;
@property (strong, nonatomic) NSArray *specification;


@end

