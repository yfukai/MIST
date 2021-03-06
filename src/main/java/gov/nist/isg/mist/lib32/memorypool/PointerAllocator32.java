// NIST-developed software is provided by NIST as a public service. You may use, copy and distribute copies of the software in any medium, provided that you keep intact this entire notice. You may improve, modify and create derivative works of the software or any portion of the software, and you may copy and distribute such modifications or works. Modified works should carry a notice stating that you changed the software and should note the date and nature of any such change. Please explicitly acknowledge the National Institute of Standards and Technology as the source of the software.

// NIST-developed software is expressly provided "AS IS." NIST MAKES NO WARRANTY OF ANY KIND, EXPRESS, IMPLIED, IN FACT OR ARISING BY OPERATION OF LAW, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, NON-INFRINGEMENT AND DATA ACCURACY. NIST NEITHER REPRESENTS NOR WARRANTS THAT THE OPERATION OF THE SOFTWARE WILL BE UNINTERRUPTED OR ERROR-FREE, OR THAT ANY DEFECTS WILL BE CORRECTED. NIST DOES NOT WARRANT OR MAKE ANY REPRESENTATIONS REGARDING THE USE OF THE SOFTWARE OR THE RESULTS THEREOF, INCLUDING BUT NOT LIMITED TO THE CORRECTNESS, ACCURACY, RELIABILITY, OR USEFULNESS OF THE SOFTWARE.

// You are solely responsible for determining the appropriateness of using and distributing the software and you assume all risks associated with its use, including but not limited to the risks and costs of program errors, compliance with applicable laws, damage to or loss of data, programs or equipment, and the unavailability or interruption of operation. This software is not intended to be used in any situation where a failure could cause risk of injury or damage to property. The software developed by NIST employees is not subject to copyright protection within the United States.



// ================================================================
//
// Author: tjb3
// Date: Apr 11, 2014 11:04:44 AM EST
//
// Time-stamp: <Apr 11, 2014 11:04:44 AM tjb3>
//
// ================================================================

package gov.nist.isg.mist.lib32.memorypool;

import org.bridj.Pointer;

import gov.nist.isg.mist.lib.memorypool.Allocator;
import gov.nist.isg.mist.lib32.imagetile.fftw.FFTW3Library32;

/**
 * Allocator type that allocates Pointer memory for use with FFTW
 *
 * The memory that is allocated is double complex, so size = size*sizeof(double)*2
 *
 * element i*2 = real element i*2+1 = imaginary
 *
 * @author Tim Blattner
 * @version 1.0
 */
public class PointerAllocator32 implements Allocator<Pointer<Float>> {

  @Override
  public Pointer<Float> allocate(int... n) {
    long size = 1;
    for (int val : n) {
      size *= val;
    }

    // Allocate complex numbers
    return FFTW3Library32.fftwf_alloc_real(size * 2);
  }

  @Override
  public Pointer<Float> deallocate(Pointer<Float> memory) {
    FFTW3Library32.fftwf_free(memory);
    return memory;
  }
}
